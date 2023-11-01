package com.wjy.practice.controller;


import com.wjy.practice.domain.Ebook;
import com.wjy.practice.req.EbookReq;
import com.wjy.practice.resp.CommonResp;
import com.wjy.practice.resp.EbookResp;
import com.wjy.practice.resp.PageResp;
import com.wjy.practice.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp list(EbookReq req) {
        CommonResp<PageResp<EbookResp>> resp = new CommonResp<>();
        PageResp<EbookResp> list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }
}
