package mad.easychinese.database;


public class strokeRecord {
    private int image;
    private String text;
    private String pinyin;

    public int getImage() {
        return image;
    }
    public void setImage(int image){
        this.image = image;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getPinyin() {
        return pinyin;
    }
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
    @Override
    public String toString(){
        return strokeContract.User.COLUMN_TEXT + ":" + this.text +
                "," + strokeContract.User.COLUMN_PINYIN + ":" + this.pinyin;
    }
}
