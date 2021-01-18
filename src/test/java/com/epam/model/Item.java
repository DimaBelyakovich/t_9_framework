package com.epam.model;

import java.util.StringJoiner;

public class Item {
    private String title;
    private boolean isFavourite;
    private boolean isCompared;

    public Item() {
        this.title = "";
        this.isFavourite = false;
        this.isCompared = false;
    }

    public Item(String title) {
        this.title = title;
        this.isFavourite = false;
        this.isCompared = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public boolean isCompared() {
        return isCompared;
    }

    public void setCompared(boolean compared) {
        isCompared = compared;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (isFavourite != item.isFavourite) return false;
        if (isCompared != item.isCompared) return false;
        return title != null ? title.equals(item.title) : item.title == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (isFavourite ? 1 : 0);
        result = 31 * result + (isCompared ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Item.class.getSimpleName() + "[", "]")
                .add("title='" + title + "'")
                .add("isFavourite=" + isFavourite)
                .add("isCompared=" + isCompared)
                .toString();
    }
}
