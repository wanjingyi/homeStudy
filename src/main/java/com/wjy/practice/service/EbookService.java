package com.wjy.practice.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wjy.practice.domain.Ebook;
import com.wjy.practice.domain.EbookExample;
import com.wjy.practice.mapper.EbookMapper;
import com.wjy.practice.req.EbookReq;
import com.wjy.practice.resp.EbookResp;
import com.wjy.practice.resp.PageResp;
import com.wjy.practice.utils.CopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);

    @Resource
    private EbookMapper ebookMapper;

    public PageResp<EbookResp> list(EbookReq req) {
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();

        if (!ObjectUtils.isEmpty(req.getName())) {
            criteria.andNameLike("%" + req.getName() + "%");
        }
        PageHelper.startPage(req.getPage(), req.getSize());

        List<Ebook> ebookslist = ebookMapper.selectByExample(ebookExample);

        PageInfo<Ebook> ebookPageInfo = new PageInfo<>(ebookslist);

        LOG.info("总行数: {}", ebookPageInfo.getTotal());
        LOG.info("总页数: {}", ebookPageInfo.getPages());

        //列表数据
        List<EbookResp> ebookResps = CopyUtil.copyList(ebookslist, EbookResp.class);

        PageResp<EbookResp> pageResp = new PageResp();
        pageResp.setTotal(ebookPageInfo.getTotal());
        pageResp.setList(ebookResps);

        return pageResp;
    }
}
