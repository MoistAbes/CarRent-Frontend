package com.example.rentcarfrontend.domain;

public enum WebSearchEditor {

    INSTANCE;

    private String review ="";



    public String editBody(String body){


        String removeBeg = body.substring(9);
        String removeEnd = removeBeg.substring(0, removeBeg.length() - 2);

        char[] test = removeEnd.toCharArray();

        for (int i = 0; i < test.length; i++){
            if (test[i] == 92){
                test[i] = '\n';
                test[i + 1] = ' ';
            }
        }

        return String.valueOf(test);

    }
}
