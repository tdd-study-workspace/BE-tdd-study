package com.tdd.study.betddstudy.api.tag.service;

import com.tdd.study.betddstudy.api.tag.repository.ArticleTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleTagService {

    private final ArticleTagRepository articleTagRepository;

}
