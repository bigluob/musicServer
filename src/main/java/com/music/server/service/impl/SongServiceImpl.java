package com.music.server.service.impl;

import com.music.server.mapper.SongMapper;
import com.music.server.model.Song;
import com.music.server.model.SongComment;
import com.music.server.service.SongService;
import com.qianxinyao.analysis.jieba.keyword.Keyword;
import com.qianxinyao.analysis.jieba.keyword.TFIDFAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

@Service("SongService")
public class SongServiceImpl implements SongService {
    @Autowired
    private SongMapper songMapper;

    @Override
    public List<Song> searchSongByName(String name){

        return songMapper.searchSongByName(name);
    }

    @Override
    public List<SongComment> getCommentMapById(BigInteger sid){
        String comments = (songMapper.getCommentsById(sid));
        int topN=16;
        List<SongComment> commentList = new ArrayList<>();
        TFIDFAnalyzer tfidfAnalyzer=new TFIDFAnalyzer();
        List<Keyword> list=tfidfAnalyzer.analyze(comments,topN);
        for(Keyword word:list)
        {
//            System.out.print(word.getName()+":"+word.getTfidfvalue()+",");
            String name = word.getName();
            Double value = word.getTfidfvalue();
            SongComment songComment = new SongComment();
            songComment.setComment(name);
            songComment.setValue(value);
            commentList.add(songComment);
        }
//        System.out.println(commentList.toString());
        return commentList;
    }
}