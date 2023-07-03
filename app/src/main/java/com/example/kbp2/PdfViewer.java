package com.example.kbp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;

import java.util.List;

public class PdfViewer extends AppCompatActivity {

    private PDFView pdfView;
    Integer pageNumber = 0;
    String pdfFileName, url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer2);

        pdfView = findViewById(R.id.pdfView);

        url = getIntent().getStringExtra("pdfUrl");
        displayFromAsset(url);
    }

    private void displayFromAsset(String url) {
        pdfFileName = url;

        pdfView.fromAsset(url)
                .defaultPage(pageNumber)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .onPageChange(new OnPageChangeListener() {
                    @Override
                    public void onPageChanged(int page, int pageCount) {
                        pageNumber = page;
                    }
                })
                .enableAnnotationRendering(true)
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {
                        PdfDocument.Meta meta = pdfView.getDocumentMeta();
                        printBookmarksTree(pdfView.getTableOfContents(), "-");
                    }
                })
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }


    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {
            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }
}