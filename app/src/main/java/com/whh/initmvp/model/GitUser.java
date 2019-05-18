package com.whh.initmvp.model;

/**
 * Created by wuhuihui on 2019/5/18.
 */

public class GitUser {
    /**
     * "login": "basil2style",
     * "id": 1285344,
     * "node_id": "MDQ6VXNlcjEyODUzNDQ=",
     * "avatar_url": "https://avatars1.githubusercontent.com/u/1285344?v=4",
     * "gravatar_id": "",
     * "url": "https://api.github.com/users/basil2style",
     * "html_url": "https://github.com/basil2style",
     * "followers_url": "https://api.github.com/users/basil2style/followers",
     * "following_url": "https://api.github.com/users/basil2style/following{/other_user}",
     * "gists_url": "https://api.github.com/users/basil2style/gists{/gist_id}",
     * "starred_url": "https://api.github.com/users/basil2style/starred{/owner}{/repo}",
     * "subscriptions_url": "https://api.github.com/users/basil2style/subscriptions",
     * "organizations_url": "https://api.github.com/users/basil2style/orgs",
     * "repos_url": "https://api.github.com/users/basil2style/repos",
     * "events_url": "https://api.github.com/users/basil2style/events{/privacy}",
     * "received_events_url": "https://api.github.com/users/basil2style/received_events",
     * "type": "User",
     * "site_admin": false,
     * "name": "Basil Alias",
     * "company": "https://www.ribitt.io/",
     * "blog": "https://www.linkedin.com/in/basilalias92/",
     * "location": "Toronto, Canada",
     * "email": null,
     * "hireable": true,
     * "bio": "Developer | Marketer | Reader | Cinephile | Entrepreneur",
     * "public_repos": 92,
     * "public_gists": 11,
     * "followers": 81,
     * "following": 94,
     * "created_at": "2011-12-26T00:17:22Z",
     * "updated_at": "2019-05-15T19:54:46Z"
     */
    private String login;
    private double id;
    private String node_id;
    private String avatar_url;
    private String gravatar_id;
    private String url;
    private String html_url;
    private String followers_url;
    private String following_url;
    private String gists_url;
    private String starred_url;
    private String subscriptions_url;
    private String organizations_url;
    private String repos_url;
    private String events_url;
    private String received_events_url;
    private String type;
    private boolean site_admin;
    private String name;
    private String company;
    private String blog;
    private String location;
    private String email;
    private String hireable;
    private String bio;
    private String public_repos;
    private String public_gists;
    private String followers;
    private String following;
    private String created_at;
    private String updated_at;

    public GitUser(String login, double id, String node_id, String avatar_url,
                   String gravatar_id, String url, String html_url, String followers_url,
                   String following_url, String gists_url, String starred_url,
                   String subscriptions_url, String organizations_url, String repos_url,
                   String events_url, String received_events_url, String type, boolean site_admin,
                   String name, String company, String blog, String location, String email,
                   String hireable, String bio, String public_repos, String public_gists, String followers,
                   String following, String created_at, String updated_at) {
        this.login = login;
        this.id = id;
        this.node_id = node_id;
        this.avatar_url = avatar_url;
        this.gravatar_id = gravatar_id;
        this.url = url;
        this.html_url = html_url;
        this.followers_url = followers_url;
        this.following_url = following_url;
        this.gists_url = gists_url;
        this.starred_url = starred_url;
        this.subscriptions_url = subscriptions_url;
        this.organizations_url = organizations_url;
        this.repos_url = repos_url;
        this.events_url = events_url;
        this.received_events_url = received_events_url;
        this.type = type;
        this.site_admin = site_admin;
        this.name = name;
        this.company = company;
        this.blog = blog;
        this.location = location;
        this.email = email;
        this.hireable = hireable;
        this.bio = bio;
        this.public_repos = public_repos;
        this.public_gists = public_gists;
        this.followers = followers;
        this.following = following;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getGravatar_id() {
        return gravatar_id;
    }

    public void setGravatar_id(String gravatar_id) {
        this.gravatar_id = gravatar_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getFollowers_url() {
        return followers_url;
    }

    public void setFollowers_url(String followers_url) {
        this.followers_url = followers_url;
    }

    public String getFollowing_url() {
        return following_url;
    }

    public void setFollowing_url(String following_url) {
        this.following_url = following_url;
    }

    public String getGists_url() {
        return gists_url;
    }

    public void setGists_url(String gists_url) {
        this.gists_url = gists_url;
    }

    public String getStarred_url() {
        return starred_url;
    }

    public void setStarred_url(String starred_url) {
        this.starred_url = starred_url;
    }

    public String getSubscriptions_url() {
        return subscriptions_url;
    }

    public void setSubscriptions_url(String subscriptions_url) {
        this.subscriptions_url = subscriptions_url;
    }

    public String getOrganizations_url() {
        return organizations_url;
    }

    public void setOrganizations_url(String organizations_url) {
        this.organizations_url = organizations_url;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    public String getEvents_url() {
        return events_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    public String getReceived_events_url() {
        return received_events_url;
    }

    public void setReceived_events_url(String received_events_url) {
        this.received_events_url = received_events_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSite_admin() {
        return site_admin;
    }

    public void setSite_admin(boolean site_admin) {
        this.site_admin = site_admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHireable() {
        return hireable;
    }

    public void setHireable(String hireable) {
        this.hireable = hireable;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPublic_repos() {
        return public_repos;
    }

    public void setPublic_repos(String public_repos) {
        this.public_repos = public_repos;
    }

    public String getPublic_gists() {
        return public_gists;
    }

    public void setPublic_gists(String public_gists) {
        this.public_gists = public_gists;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "GitUser{" +
                "login='" + login + '\'' +
                ", id=" + id +
                ", node_id='" + node_id + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", gravatar_id='" + gravatar_id + '\'' +
                ", url='" + url + '\'' +
                ", html_url='" + html_url + '\'' +
                ", followers_url='" + followers_url + '\'' +
                ", following_url='" + following_url + '\'' +
                ", gists_url='" + gists_url + '\'' +
                ", starred_url='" + starred_url + '\'' +
                ", subscriptions_url='" + subscriptions_url + '\'' +
                ", organizations_url='" + organizations_url + '\'' +
                ", repos_url='" + repos_url + '\'' +
                ", events_url='" + events_url + '\'' +
                ", received_events_url='" + received_events_url + '\'' +
                ", type='" + type + '\'' +
                ", site_admin=" + site_admin +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", blog='" + blog + '\'' +
                ", location='" + location + '\'' +
                ", email='" + email + '\'' +
                ", hireable='" + hireable + '\'' +
                ", bio='" + bio + '\'' +
                ", public_repos='" + public_repos + '\'' +
                ", public_gists='" + public_gists + '\'' +
                ", followers='" + followers + '\'' +
                ", following='" + following + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }
}
