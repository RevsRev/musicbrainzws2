package github.com.rev.musicbrainz.query.search.readysearches;

import java.util.List;

public interface  EntitySearchInterface  {

    public List  getFullList();
    public List  getFirstPage();
    public List  getNextPage();

    public boolean hasMore();
}
