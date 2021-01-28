import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class My_notes implements Parcelable {
    private String title;
    private String note;
    private String date;

    public My_notes(){
    }
    public My_notes(String title, String note, String date){
    setName(title);
    setNote(note);
    setDate(date);
    }


    protected My_notes(Parcel in) {
        title = in.readString();
        note = in.readString();
        date = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(note);
        dest.writeString(date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<My_notes> CREATOR = new Creator<My_notes>() {
        @Override
        public My_notes createFromParcel(Parcel in) {
            return new My_notes(in);
        }

        @Override
        public My_notes[] newArray(int size) {
            return new My_notes[size];
        }
    };

    public String getName() {
        return title;
    }

    public void setName(String name) {
        this.title = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
