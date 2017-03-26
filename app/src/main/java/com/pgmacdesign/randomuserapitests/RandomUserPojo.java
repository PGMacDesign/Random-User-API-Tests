package com.pgmacdesign.randomuserapitests;

import com.google.gson.annotations.SerializedName;

/**
 * Data model pojo to match json return
 * Created by PatrickSSD2 on 3/25/2017.
 */
public class RandomUserPojo {

    @SerializedName("info")
    private RUInfo info;
    @SerializedName("results")
    private RUResults[] results;

    public RUInfo getInfo() {
        return info;
    }

    public void setInfo(RUInfo info) {
        this.info = info;
    }

    public RUResults[] getResults() {
        return results;
    }

    public void setResults(RUResults[] results) {
        this.results = results;
    }

    public static class RUInfo {
        @SerializedName("seed")
        private String seed;
        @SerializedName("version")
        private String version;
        @SerializedName("page")
        private int page;
        @SerializedName("results")
        private int results;

        public String getSeed() {
            return seed;
        }

        public void setSeed(String seed) {
            this.seed = seed;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getResults() {
            return results;
        }

        public void setResults(int results) {
            this.results = results;
        }
    }

    public static class RUIDInfo {
        @SerializedName("name")
        private String idName;
        @SerializedName("value")
        private String idValue;

        public String getIdName() {
            return idName;
        }

        public void setIdName(String idName) {
            this.idName = idName;
        }

        public String getIdValue() {
            return idValue;
        }

        public void setIdValue(String idValue) {
            this.idValue = idValue;
        }
    }

    public static class RUPicture {
        @SerializedName("large")
        private String imageLarge;
        @SerializedName("medium")
        private String imageMedium;
        @SerializedName("thumbnail")
        private String imageThumbnail;

        public String getImageLarge() {
            return imageLarge;
        }

        public void setImageLarge(String imageLarge) {
            this.imageLarge = imageLarge;
        }

        public String getImageMedium() {
            return imageMedium;
        }

        public void setImageMedium(String imageMedium) {
            this.imageMedium = imageMedium;
        }

        public String getImageThumbnail() {
            return imageThumbnail;
        }

        public void setImageThumbnail(String imageThumbnail) {
            this.imageThumbnail = imageThumbnail;
        }
    }

    public static class RUName {
        @SerializedName("title")
        private String title;
        @SerializedName("first")
        private String first;
        @SerializedName("last")
        private String last;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }
    }

    public static class RULocation {
        @SerializedName("street")
        private String street;
        @SerializedName("city")
        private String city;
        @SerializedName("state")
        private String state;
        @SerializedName("postcode")
        private String postcode;

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }
    }

    public static class RUResults {
        @SerializedName("gender")
        private String gender;
        @SerializedName("email")
        private String email;
        @SerializedName("name")
        private RUName name;
        @SerializedName("location")
        private RULocation location;
        @SerializedName("dob")
        private String dob;
        @SerializedName("registered")
        private String registered;
        @SerializedName("phone")
        private String phone;
        @SerializedName("cell")
        private String cell;
        @SerializedName("id")
        private RUIDInfo id;
        @SerializedName("picture")
        private RUPicture picture;
        @SerializedName("nat")
        private String nationality;

        public RUName getName() {
            return name;
        }

        public void setName(RUName name) {
            this.name = name;
        }

        public RULocation getLocation() {
            return location;
        }

        public void setLocation(RULocation location) {
            this.location = location;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getRegistered() {
            return registered;
        }

        public void setRegistered(String registered) {
            this.registered = registered;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCell() {
            return cell;
        }

        public void setCell(String cell) {
            this.cell = cell;
        }

        public RUIDInfo getId() {
            return id;
        }

        public void setId(RUIDInfo id) {
            this.id = id;
        }

        public RUPicture getPicture() {
            return picture;
        }

        public void setPicture(RUPicture picture) {
            this.picture = picture;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}

