package br.ce.wcaquino.matchers;

import br.ce.wcaquino.utils.DataUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DiaSemanaMatcher extends TypeSafeMatcher<Date> {
    private Integer diasemana;


    public DiaSemanaMatcher(Integer diaSemana) {
        this.diasemana = diaSemana;
    }

    @Override
    protected boolean matchesSafely(Date data) {//checkThat
        return DataUtils.verificarDiaSemana(data, diasemana);
    }

    @Override
    public void describeTo(Description description) {
        Calendar data = Calendar.getInstance();
        data.set(Calendar.DAY_OF_WEEK, diasemana);
        String dataExtenso = data.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale("pt", "BR"));
        description.appendText(dataExtenso);

    }
}
