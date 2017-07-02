package com.kaivanshah.todolist;

/**
 * Created by kaivanrasiklal.s on 29-03-2017.
 */

public class ToDoList_CLASS {

    //private variables
    int _id;
    String _title;
    String _Description;
    String _DueDate;
    int _status;

    // Empty constructor
    public ToDoList_CLASS(){

    }
    // constructor
    public ToDoList_CLASS(int id, String title, String Description, String DueDate, int status){
        this._id = id;
        this._title = title;
        this._Description = Description;
        this._DueDate = DueDate;
        this._status = status;
    }

    // constructor
    public ToDoList_CLASS(String title, String Description, String DueDate, int status){
        this._title = title;
        this._Description = Description;
        this._DueDate = DueDate;
        this._status = status;
    }

    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting title
    public String getTitle(){
        return this._title;
    }

    // setting title
    public void setTitle(String title){
        this._title = title;
    }

    // getting Description
    public String getDescription(){
        return this._Description;
    }

    // setting Description
    public void setDescription(String Description){
        this._Description = Description;
    }


    // getting DueDate
    public String getDueDate(){
        return this._DueDate;
    }

    // setting DueDate
    public void setDueDate(String DueDate){
        this._DueDate = DueDate;
    }


    // getting status
    public int getStatus(){
        return this._status;
    }

    // setting status
    public void setStatus(int status){
        this._status = status;
    }

}
