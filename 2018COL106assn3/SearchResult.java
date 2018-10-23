public class SearchResult implements Comparable<SearchResult>{
    PageEntry page;
    float relevance;
    public SearchResult(PageEntry p, float r){
        page = p;
        relevance = r;
    }
    public PageEntry getPageEntry(){
        return page;
    }
    public float getRelevance(){
        return relevance;
    }
    public int compareTo(SearchResult otherObject){
        if (this.relevance>otherObject.relevance){
            return 1;
        } else if(this.relevance == otherObject.relevance){
            return 0;
        } else{
            return -1;
        }
    }


}