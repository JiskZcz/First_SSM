package com.my.crud.dao;

import com.my.crud.bean.Pos;
import com.my.crud.bean.PosExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PosMapper {
    long countByExample(PosExample example);

    int deleteByExample(PosExample example);

    int deleteByPrimaryKey(Integer sid);

    int insert(Pos record);

    int insertSelective(Pos record);

    List<Pos> selectByExample(PosExample example);

    Pos selectByPrimaryKey(Integer sid);

    int updateByExampleSelective(@Param("record") Pos record, @Param("example") PosExample example);

    int updateByExample(@Param("record") Pos record, @Param("example") PosExample example);

    int updateByPrimaryKeySelective(Pos record);

    int updateByPrimaryKey(Pos record);
}