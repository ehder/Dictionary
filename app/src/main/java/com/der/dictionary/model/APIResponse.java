package com.der.dictionary.model;

import java.util.List;

public class APIResponse {

    private String word = "";
    //private String phonetic = "";
    private List<Phonetics> phonetics = null;
    //private String origin;
    private List<Meaning> meanings = null;


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

/*    public String getPhonetic() {
        return phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }*/

    public List<Phonetics> getPhonetics() {
        return phonetics;
    }

    public void setPhonetics(List<Phonetics> phonetics) {
        this.phonetics = phonetics;
    }

/*    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }*/

    public List<Meaning> getMeanings() {
        return meanings;
    }

    public void setMeanings(List<Meaning> meanings) {
        this.meanings = meanings;
    }
}
