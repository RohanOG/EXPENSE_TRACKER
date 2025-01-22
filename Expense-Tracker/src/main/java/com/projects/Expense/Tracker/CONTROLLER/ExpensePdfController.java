package com.projects.Expense.Tracker.CONTROLLER;

import com.projects.Expense.Tracker.ENTITY.Expenses;
import com.projects.Expense.Tracker.SERVICE.ExpenseService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/pdf")
public class ExpensePdfController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/download")
    public void downloadPDF(HttpServletResponse response) throws IOException {
        List<Expenses> expenses = expenseService.getAllExpense();

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=expenses.pdf");

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 750);
                contentStream.setLeading(20f);

                contentStream.showText("Expense Report");
                contentStream.newLine();
                contentStream.setFont(PDType1Font.HELVETICA, 12);

                for (Expenses expense : expenses) {
                    contentStream.showText(expense.getCategory() + ": $" + expense.getAmount());
                    contentStream.newLine();
                }

                contentStream.endText();
            }

            document.save(response.getOutputStream());
        }
    }
}
