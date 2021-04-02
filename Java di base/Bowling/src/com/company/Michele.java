package com.company;

import java.util.ArrayList;
import java.util.List;

public class Michele {

    public static void main(String[] args) {

        List<String> amiciDiMichele = new ArrayList<>();
        amiciDiMichele.add("Simone Briffa");
        amiciDiMichele.remove("Simone Briffa");
        System.out.println(amiciDiMichele.isEmpty());

    }
}
