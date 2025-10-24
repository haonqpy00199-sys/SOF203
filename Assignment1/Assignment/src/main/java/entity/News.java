package entity;

import java.util.Date;

public class News {
    private String Id;
    private String Title;
    private String Content;
    private String Image;
    private Date PostedDate;
    private String Author;
    private int ViewCount;
    private String CategoryId;
    private Boolean Home;
    private Boolean Status;

    private String CategoryName;
    private String AuthorName;

    public News() { }

    public String getId() { return Id; }
    public void setId(String id) { Id = id; }

    public String getTitle() { return Title; }
    public void setTitle(String title) { Title = title; }

    public String getContent() { return Content; }
    public void setContent(String content) { Content = content; }

    public String getImage() { return Image; }
    public void setImage(String image) { Image = image; }

    public Date getPostedDate() { return PostedDate; }
    public void setPostedDate(Date postedDate) { PostedDate = postedDate; }

    public String getAuthor() { return Author; }
    public void setAuthor(String author) { Author = author; }

    public int getViewCount() { return ViewCount; }
    public void setViewCount(int viewCount) { ViewCount = viewCount; }

    public String getCategoryId() { return CategoryId; }
    public void setCategoryId(String categoryId) { CategoryId = categoryId; }

    public Boolean getHome() { return Home; }
    public void setHome(Boolean home) { Home = home; }

    public Boolean getStatus() { return Status; }
    public void setStatus(Boolean status) { Status = status; }

    public String getCategoryName() { return CategoryName; }
    public void setCategoryName(String categoryName) { CategoryName = categoryName; }

    public String getAuthorName() { return AuthorName; }
    public void setAuthorName(String authorName) { AuthorName = authorName; }
}
