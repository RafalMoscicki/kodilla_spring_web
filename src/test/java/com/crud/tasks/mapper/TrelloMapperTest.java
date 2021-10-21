package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TrelloMapperTest {

    private final TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    void mapToList() {
        //Given
        List<TrelloListDto> trelloListDtos = Arrays.asList(
                new TrelloListDto("1", "name1", true),
                new TrelloListDto("2", "name2", false)
        );

        //When
        Collection<TrelloList> result = trelloMapper.mapToList(trelloListDtos);

        //Then
        Assertions.assertThat(result)
                .extracting(TrelloList::getId, TrelloList::getName, TrelloList::isClosed)
                .containsExactly(
                        tuple("1", "name1", true),
                        tuple("2", "name2", false));
    }

    @Test
    void mapToListDto() {
        //Given
        List<TrelloList> trelloLists = Arrays.asList(
                new TrelloList("1", "name1", true),
                new TrelloList("2", "name2", false)
        );

        //When
        Collection<TrelloListDto> result = trelloMapper.mapToListDto(trelloLists);

        //Then
        assertThat(result)
                .extracting(TrelloListDto::getId, TrelloListDto::getName, TrelloListDto::isClosed)
                .containsExactly(
                        tuple("1", "name1", true),
                        tuple("2", "name2", false));
    }

    @Test
    void mapToBoards() {
        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(new TrelloListDto("2", "name2", true));
        List<TrelloBoardDto> trelloBoardDtos = Collections.singletonList(
                new TrelloBoardDto("1", "name1", trelloListDtos)
        );

        //When
        List<TrelloBoard> result = trelloMapper.mapToBoards(trelloBoardDtos);

        //Then
        assertThat(result)
                .extracting(TrelloBoard::getId, TrelloBoard::getName, TrelloBoard::getLists)
                .containsExactly(
                        tuple("1", "name1", Arrays.asList(new TrelloList("2", "name2", true)))
                );
    }

    @Test
    void mapToBoardsDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("2", "name2", false));
        List<TrelloBoard> trelloBoards = Collections.singletonList(
                new TrelloBoard("1", "name1", trelloLists)
        );

        //When
        Collection<TrelloBoardDto> result = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        assertThat(result)
                .extracting(TrelloBoardDto::getId, TrelloBoardDto::getName, TrelloBoardDto::getLists)
                .containsExactly(
                        tuple("1", "name1", Arrays.asList(new TrelloListDto("2", "name2", false)))
                );
    }

    @Test
    void mapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("name1", "desc1", "pos1", "123");

        //When
        TrelloCardDto result = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals(result, new TrelloCardDto("name1", "desc1", "pos1", "123"));
    }

    @Test
    void mapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name1", "desc1", "pos1", "123");

        //When
        TrelloCard result = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals(result, new TrelloCard("name1", "desc1", "pos1", "123"));
    }
}