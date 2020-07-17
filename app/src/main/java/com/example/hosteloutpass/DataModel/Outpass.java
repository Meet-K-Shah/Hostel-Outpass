package com.example.hosteloutpass.DataModel;

public class Outpass {

    private String name,enrollment_no,sem,room_no,out_date,out_time,student_contact,parent_contact,parent_consent,reason,gender,faculty_name,accept_or_denied;

    public Outpass(){}

    public Outpass(String name, String enrollment_no, String sem, String room_no, String out_date, String out_time, String student_contact,
                   String parent_contact, String parent_consent, String reason, String gender, String faculty_name, String accept_or_denied) {

        this.name = name;
        this.enrollment_no = enrollment_no;
        this.sem = sem;
        this.room_no = room_no;
        this.out_date = out_date;
        this.out_time = out_time;
        this.student_contact = student_contact;
        this.parent_contact = parent_contact;
        this.parent_consent = parent_consent;
        this.reason = reason;
        this.gender = gender;
        this.faculty_name = faculty_name;
        this.accept_or_denied = accept_or_denied;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnrollment_no() {
        return enrollment_no;
    }

    public void setEnrollment_no(String enrollment_no) {
        this.enrollment_no = enrollment_no;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getRoom_no() {
        return room_no;
    }

    public void setRoom_no(String room_no) {
        this.room_no = room_no;
    }

    public String getOut_date() {
        return out_date;
    }

    public void setOut_date(String out_date) {
        this.out_date = out_date;
    }

    public String getOut_time() {
        return out_time;
    }

    public void setOut_time(String out_time) {
        this.out_time = out_time;
    }

    public String getStudent_contact() {
        return student_contact;
    }

    public void setStudent_contact(String student_contact) {
        this.student_contact = student_contact;
    }

    public String getParent_contact() {
        return parent_contact;
    }

    public void setParent_contact(String parent_conatact) {
        this.parent_contact = parent_contact;
    }

    public String getParent_consent() {
        return parent_consent;
    }

    public void setParent_consent(String parent_consent) {
        this.parent_consent = parent_consent;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFaculty_name() {
        return faculty_name;
    }

    public void setFaculty_name(String faculty_name) {
        this.faculty_name = faculty_name;
    }

    public String getAccept_or_denied() {
        return accept_or_denied;
    }

    public void setAccept_or_denied(String faculty_name) {
        this.accept_or_denied = accept_or_denied;
    }


}
