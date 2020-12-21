package com.liuyadong.brainstorm.service.impl;

import com.liuyadong.brainstorm.entity.Notice;
import com.liuyadong.brainstorm.entity.custom.NoticeCustom;
import com.liuyadong.brainstorm.mapper.NoticeMapper;
import com.liuyadong.brainstorm.mapper.custom.NoticeMapperCustom;
import com.liuyadong.brainstorm.service.NoticeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapperCustom noticeMapperCustom;

    @Autowired
    private NoticeMapper noticeMapper;


    @Override
    public List<NoticeCustom> listNotice(Integer status) throws Exception {
        List<NoticeCustom> noticeList = noticeMapperCustom.listNotice(status);
        return noticeList;
    }

    @Override
    public void insertNotice(Notice notice) throws Exception {
        noticeMapper.insertSelective(notice);
    }

    @Override
    public void deleteNotice(Integer id) throws Exception {
        noticeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateNotice(Notice notice) throws Exception {
        noticeMapper.updateByPrimaryKeySelective(notice);
    }

    @Override
    public NoticeCustom getNoticeById(Integer id) throws Exception {
        Notice notice = noticeMapper.selectByPrimaryKey(id);
        NoticeCustom noticeCustom = new NoticeCustom();
        BeanUtils.copyProperties(notice,noticeCustom);
        return noticeCustom;
    }

}
