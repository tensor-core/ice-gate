package com.cssrc.usercenter.bean;

import com.cssrc.usercenter.entity.BaseUser;

import java.util.List;


public class GroupUsers {
    List<BaseUser> members ;
    List<BaseUser> leaders;

    public GroupUsers() {
    }

    public GroupUsers(List<BaseUser> members, List<BaseUser> leaders) {
        this.members = members;
        this.leaders = leaders;
    }

    public List<BaseUser> getMembers() {
        return members;
    }

    public void setMembers(List<BaseUser> members) {
        this.members = members;
    }

    public List<BaseUser> getLeaders() {
        return leaders;
    }

    public void setLeaders(List<BaseUser> leaders) {
        this.leaders = leaders;
    }
}
