package org.example.november_market_2.core.services;

import java.util.ArrayList;

public class PhoneNumberSymbols {                 //  класс допустимых символов для номера телефона
    private static ArrayList<Character> characters;

    public PhoneNumberSymbols() {
        characters = new ArrayList<>(14);
        characters.add('0'); characters.add('1'); characters.add('2'); characters.add('3'); characters.add('4'); characters.add('5');
        characters.add('6'); characters.add('7'); characters.add('8'); characters.add('9'); characters.add(' '); characters.add('-');
        characters.add('('); characters.add(')');
    }

    public static ArrayList<Character> getCharacters() {
        return characters;
    }
}
