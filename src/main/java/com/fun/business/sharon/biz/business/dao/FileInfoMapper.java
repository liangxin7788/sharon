package com.fun.business.sharon.biz.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fun.business.sharon.biz.business.bean.FileInfo;
import com.fun.business.sharon.biz.business.vo.FileSearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liangxin
 * @since 2019-06-11
 */
@Repository
public interface FileInfoMapper extends BaseMapper<FileInfo> {

    int getFileInfoListCount(@Param("vo") FileSearchVo vo);

    List<FileInfo> getFileInfoList(@Param("vo")FileSearchVo vo, int offset, int pageSize);
}
