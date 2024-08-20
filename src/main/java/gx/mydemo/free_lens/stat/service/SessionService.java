package gx.mydemo.free_lens.stat.service;

import gx.mydemo.free_lens.stat.dao.Session;
import gx.mydemo.free_lens.stat.dao.SessionCollection;
import gx.mydemo.free_lens.stat.repository.SessionCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gx.mydemo.free_lens.stat.dto.DataStat;

import java.util.List;

@Service
public class SessionService {
    @Autowired
    private SessionCollectionRepository sessionCollectionRepository;

    public DataStat stat_session(String collection_name) {
        // 访问数据库
        System.err.println("stat_session");
        List<SessionCollection> sessionCollections = sessionCollectionRepository.findAll();
        System.out.println(sessionCollections.size());
        return new DataStat(sessionCollections.size());
        // return 10;
    }
}