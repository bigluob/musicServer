package com.music.server.mapper;


import com.music.server.model.Album;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AlbumMapper {

    List<Album> searchAlbumByName(String name);
}
