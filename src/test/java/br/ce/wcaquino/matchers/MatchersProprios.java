package br.ce.wcaquino.matchers;

import java.util.Calendar;

public class MatchersProprios {

    public static DiaSemanaMatcher caiEm(Integer diasemana){
     return new DiaSemanaMatcher(diasemana);
    }

    public static DiaSemanaMatcher caiNumaSegunda(){
        return new DiaSemanaMatcher(Calendar.MONDAY);
    }

    public static CompararDiasMatcher ehHoje(){
        return new CompararDiasMatcher(0);
    }

    public static CompararDiasMatcher obterDataComDiferencaDias(Integer proximoDiaQTD){
        return new CompararDiasMatcher(proximoDiaQTD);
    }
}
