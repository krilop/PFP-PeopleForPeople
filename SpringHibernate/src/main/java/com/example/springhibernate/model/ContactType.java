package com.example.springhibernate.model;

public enum ContactType {
    PHONE_NUMBER("PHONE_NUMBER"),
    TELEGRAM("TELEGRAM"),
    SPOTIFY("SPOTIFY"),
    INSTAGRAM("INSTAGRAM");

    private String text;
    ContactType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    // Дополнительный статический метод для получения ContactType из текста
    public static ContactType fromText(String text) {
        for (ContactType value : ContactType.values()) {
            if (value.text.equalsIgnoreCase(text)) {
                return value;
            }
        }
        return null; // Возвращаем null, если не найдено совпадение
    }
}
