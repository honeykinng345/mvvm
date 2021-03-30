package j.e.c.mvvm.utilis;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import j.e.c.mvvm.models.Note;


public class SampleDataProvider {
    static final String SAMPLETEXT1 = "A simple note";
    static final String SAMPLETEXT2 = "A note with a \n line feed";
    static final String SAMPLETEXT3 = "PS. This is for someone who is using java and got stuck for a while like I did and this SO answer comes up in google all the time.";

    static Date getDate(int diffamount){
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MILLISECOND, diffamount);
        return calendar.getTime();
    }

    public static List<Note> getSampleData(){
        List<Note> notes = new ArrayList<>();

        notes.add(new Note(getDate(0), SAMPLETEXT1));
        notes.add(new Note(getDate(-1), SAMPLETEXT2));
        notes.add(new Note(getDate(-2), SAMPLETEXT3));

        return notes;
    }
}
