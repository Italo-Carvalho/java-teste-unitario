package br.ce.wcaquino.matchers;

import br.ce.wcaquino.utils.DataUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Date;

import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;


public class CompararDiasMatcher extends TypeSafeMatcher<Date> {
    private Integer proximoDia;


    public CompararDiasMatcher(Integer proximoDia) {
        this.proximoDia = proximoDia;
    }

    @Override
    protected boolean matchesSafely(Date date) {
       return DataUtils.isMesmaData(date, obterDataComDiferencaDias(proximoDia));
    }

    @Override
    public void describeTo(Description description) {

    }
}
