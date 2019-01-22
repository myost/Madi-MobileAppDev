package com.example.madi.yostfinal;

import android.view.View;

public class Pizza {
    private String pizzaDescr;
    private String pizzeria;
    private String pizzeriaURL;

    private void setPizzeriaInfo(Boolean gf, String crust){
        if(gf){
            pizzeria = "Boss Lady";
            pizzeriaURL = "https://bossladypizza.com/";

        }else if(crust.equals("thin")){
            pizzeria = "Pizzeria Locale";
            pizzeriaURL = "https://localeboulder.com/";

        }else if(crust.equals("thick")){
            pizzeria = "Backcountry Pizza";
            pizzeriaURL = "https://backcountrypizzaandtaphouse.info/";
        }
    }

    public void setPizzaDescr(String descr){
        pizzaDescr = descr;
    }

    public void setPizzeria(Boolean glutenFree, String crust){
        setPizzeriaInfo(glutenFree, crust);

    }

    public void setPizzeriaURL(Boolean glutenFree, String crust){
        setPizzeriaInfo(glutenFree, crust);
    }

    public String getPizzaDescr(){
        return pizzaDescr;
    }

    public String getPizzeria(){
        return pizzeria;
    }

    public String getPizzeriaURL(){
        return pizzeriaURL;
    }
}
