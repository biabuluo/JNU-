package cn.edu.jnu.devhub.model.vo;

public class HotVO {
    private int count;
    private int postId;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        return postId == ((HotVO)obj).postId;
    }
}
