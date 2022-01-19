package study.effectivejava.item7;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Array;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StackTest {

    @Captor
    ArgumentCaptor<Object[]> captorObjects;

    @InjectMocks
    private Stack subject;

    @Before
    public void setUp() throws Exception {
        subject = new Stack();
    }

    @Test
    public void When아이템을넣는다_Then아이템이들어간다() {
        //when
//        Object item = new Object();
        subject.push(anything());
        //then
    }

    @Test
    public void Given아이템이들어간다_When아이템을뺀다_Then아이템이나온다() {
        //given
        Object item = new Object();
        subject.push(item);
        //when
        Object result = subject.pop();
        //then
        assertThat(result, is(item));
    }

    @Test(expected = EmptyStackException.class)
    public void Given아이템이없다_When아이템을뺀다_Then에러발생() {
        //given
        //when
        subject.pop();
        //then
    }

    @Test
    public void Given아이템이꽉찼다_When단일아이템추가_Then아이템들어감() {
        //given
        int count = subject.getCapacity();
        List<Object> items = new ArrayList<>(count);
        while (count-- > 0)
            items.add(new Object());

        subject.pushAll(items);
        //when
        subject.push(anything());
        //then
        assertThat(subject.getCapacity(), is(subject.getCapacity() + 1));
    }

    @Test
    public void Given아이템이꽉찼다_When멀티아이템추가_Then아이템들어감() {
        //given
        int count = subject.getCapacity();
        List<Object> items = new ArrayList<>(count);
        while (count-- > 0)
            items.add(new Object());

        subject.pushAll(items);
        //when
        subject.pushAll(items);
        //then
        assertThat(subject.getCapacity(), is(subject.getCapacity() * 2));
    }

    @Test
    public void Given아이템이꽉찼다_When모든아이템을뺀다_Then아이템다빠짐() {
        //given
        int count = subject.getCapacity();
        List<Object> items = new ArrayList<>(count);
        while (count-- > 0)
            items.add(new Object());
        subject.pushAll(items);
        //when
        List<Object> result = subject.popAll();
        //then

        //TODO : Collection item 같은지 비교 어떻게?
        assertThat(items, hasItems(result));

        try {
            subject.pop();
        } catch (Exception ex) {
            assertThat(ex.getClass(), is(EmptyStackException.class));
        }
    }
}