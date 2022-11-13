package com.tdd.study.betddstudy.api.tag.service;

import com.tdd.study.betddstudy.api.tag.entity.ArticleTag;
import com.tdd.study.betddstudy.api.tag.repository.ArticleTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleTagService {

    private final ArticleTagRepository articleTagRepository;

    public List<ArticleTag> getTags() {
        return null;
    }
}
