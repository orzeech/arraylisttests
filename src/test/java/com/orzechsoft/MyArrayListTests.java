package com.orzechsoft;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("ArrayList")
class MyArrayListTests {

    ArrayList<Long> list;

    @Test
    @DisplayName("is instantiated")
    void isInstantiatedWithNew() {
        new ArrayList<>();
    }

    @Nested
    @DisplayName("When new")
    class WhenNew {

        @BeforeEach
        void createNewList() {
            list = new ArrayList<>();
        }

        @Test
        @DisplayName("is empty")
        void isEmpty() {
            assertTrue(list.isEmpty());
        }

        @Test
        @DisplayName("size is 0")
        void sizeIsZero() {
            assertEquals(0, list.size());
        }

        @Test
        @DisplayName("throws IndexOutOfBounds when trying to get an element")
        void throwsExceptionWhenTryingToGenAnElement() {
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        }

        @Test
        @DisplayName("returns an empty Iterator")
        void returnsIterator() {
            assertFalse(list.iterator().hasNext());
        }

        @Test
        @DisplayName("returns an empty ListIterator")
        void returnsListIterator() {
            assertFalse(list.listIterator().hasNext());
        }

        @Nested
        @DisplayName("After adding an element")
        class AfterAdding {

            Long anElement = 100L;

            @BeforeEach
            void addAnElement() {
                list.add(anElement);
            }

            @Test
            @DisplayName("is no longer empty")
            void isNotEmpty() {
                assertFalse(list.isEmpty());
            }

            @Test
            @DisplayName("size equals 1")
            void sizeEqualsOne() {
                assertEquals(1, list.size());
            }

            @Test
            @DisplayName("contains the element")
            void containsElement() {
                assertTrue(list.contains(anElement));
            }

            @Test
            @DisplayName("returns the element")
            void returnElementWhen() {
                assertEquals(anElement, list.get(0));
            }

            @Nested
            @DisplayName("After adding collection")
            class AfterAddingCollection {

                Long[] array = new Long[] { 200L, 300L, 400L, 500L };

                Collection<Long> collectionToAdd = new LinkedList<>(Arrays.asList(array));

                @BeforeEach
                void addCollection() {
                    list.addAll(collectionToAdd);
                }

                @Test
                @DisplayName("size equals 5")
                void sizeEqualsOne() {
                    assertEquals(5, list.size());
                }

                @Test
                @DisplayName("contains all elements")
                void containsAllElements() {
                    assertTrue(list.contains(anElement));
                    Arrays.stream(array).forEach(e -> assertTrue(list.contains(e)));
                }

                @Test
                @DisplayName("maintains order")
                void maintainsOrder() {
                    int i = 0;
                    Long[] expectedOrder = new Long[] { 100L, 200L, 300L, 400L, 500L };

                    for (Long el : list) {
                        assertEquals(expectedOrder[i++], el);
                    }
                }

                @Test
                @DisplayName("removes an element by index")
                void removesAnElementByIndex() {
                    list.remove(2);

                    assertFalse(list.contains(300L));
                    assertEquals(4, list.size());
                }

                @Test
                @DisplayName("removes an element by object")
                void removesAnElementByObject() {
                    list.remove(anElement);

                    assertFalse(list.contains(anElement));
                    assertEquals(4, list.size());
                }

                @Test
                @DisplayName("removes a collection")
                void removesCollection() {
                    list.removeAll(collectionToAdd);

                    Arrays.stream(array).forEach(e -> assertFalse(list.contains(e)));
                    assertEquals(1, list.size());
                }

                @Test
                @DisplayName("returns sub list")
                void returnsSubList() {
                    List<Long> subList = list.subList(1, 5);

                    assertEquals(Arrays.asList(array), subList);
                }

                @Test
                @DisplayName("sets an element")
                void setsAnElement() {
                    Long elementToSet = 333L;
                    list.set(2, elementToSet);

                    assertEquals(list.get(2), elementToSet);
                }

                @Test
                @DisplayName("applies 'for each' action")
                void appliesForEach() {
                    List<Long> newList = new ArrayList<>();
                    list.forEach(e -> newList.add(e));

                    assertEquals(list, newList);
                }

                @Test
                @DisplayName("sorts the list")
                void sorts() {
                    list.set(0, 600L);
                    list.add(100L);
                    list.sort(Comparator.naturalOrder());

                    Long lastElement = 0L;

                    for (Long el : list) {
                        assertTrue(el >= lastElement);
                        lastElement = el;
                    }
                }

                @Test
                @DisplayName("returns first index of a given element")
                void returnsFirstIndexOfGivenElement() {
                    long addedElement = 200L;
                    list.add(addedElement);
                    int returnedIndex = list.indexOf(addedElement);

                    assertEquals(1, returnedIndex);
                }

                @Test
                @DisplayName("returns last index of a given element")
                void returnsLastIndexOfGivenElement() {
                    long addedElement = 200L;
                    list.add(addedElement);
                    int returnedIndex = list.lastIndexOf(addedElement);

                    assertEquals(5, returnedIndex);
                }

                @Nested
                @DisplayName("After clearing")
                class AfterClearing {

                    @BeforeEach
                    void clear() {
                        list.clear();
                    }

                    @Test
                    @DisplayName("is empty")
                    void isEmptyAfterClearing() {
                        assertTrue(list.isEmpty());
                    }

                    @Test
                    @DisplayName("size equals 0")
                    void sizeEqualsZeroAfterClearing() {
                        assertEquals(0, list.size());
                    }

                    @Test
                    @DisplayName("doesn't contain the element")
                    void doesNotContainElementAfterClearing() {
                        assertFalse(list.contains(anElement));
                    }
                }
            }
        }
    }
}