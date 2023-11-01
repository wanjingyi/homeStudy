package com.wjy.practice.service;

import com.wjy.practice.domain.Ebook;
import com.wjy.practice.domain.EbookExample;
import com.wjy.practice.mapper.EbookMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;

    public List<Ebook> list(String name) {
//        return ebookMapper.selectByExample(null);
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%" + name + "%");

        List<Ebook> ebookslist = ebookMapper.selectByExample(ebookExample);

        return ebookslist;
    }
}
