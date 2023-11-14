package com.surfholicdb.surfholicdb.service;

import java.io.IOException;
import java.util.List;

public interface MainService {

    List<String> getSeaInfo(String obsCode);

    List<String> getDateApi() throws IOException;

    List<String> getFutureDateApi() throws IOException;
    
}
