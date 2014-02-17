package little.cute.View.LiplisCharListView;

import android.graphics.Bitmap;

public class LiplisCharListViewItem {
	private Bitmap imageData_;
    private String textData_;


    public LiplisCharListViewItem(String textData_, Bitmap imageData_)
    {
    	this.imageData_ = imageData_;
    	this.textData_ = textData_;
    }

    public void setImagaData(Bitmap image) {
        imageData_ = image;
    }

    public Bitmap getImageData() {
        return imageData_;
    }

    public void setTextData(String text) {
        textData_ = text;
    }

    public String getTextData() {
        return textData_;
    }
}