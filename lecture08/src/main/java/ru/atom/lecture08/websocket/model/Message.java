package ru.atom.lecture08.websocket.model;

public class Message {
    private User author;
    private String text;

    public User getAuthor() {
        return author;
    }

    public Message setAuthor(User author) {
        this.author = author;
        return this;
    }

    public String getText() {
        return text;
    }

    public Message setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Message message = (Message) obj;

        return message.author.equals(author) && message.text.equals(text);
    }

    @Override
    public String toString() {
        return "[" + author.getName() + "] " + text;
    }
}
