package edu.gonzaga;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest {
    @Test
    public void testGetCardImage() {
        // Arrange
        Card card = new Card(3, 1);

        // Act
        String expectedImagePath = "Classic/d03.png";
        String actualImagePath = card.getCardImage();

        // Assert
        assertEquals(expectedImagePath, actualImagePath);
    }
    @Test
    public void testGetCardImage2() {
        // Arrange
        Card card = new Card(1, 3);

        // Act
        String expectedImagePath = "Classic/h01.png";
        String actualImagePath = card.getCardImage();

        // Assert
        assertEquals(expectedImagePath, actualImagePath);
    }

    @Test
    public void testGetCardImage3() {
        // Arrange
        Card card = new Card(10, 2);

        // Act
        String expectedImagePath = "Classic/c10.png";
        String actualImagePath = card.getCardImage();

        // Assert
        assertEquals(expectedImagePath, actualImagePath);
    }

    @Test
    public void testGetCardImage4() {
        // Arrange
        Card card = new Card(5, 1);

        // Act
        String expectedImagePath = "Classic/d05.png";
        String actualImagePath = card.getCardImage();

        // Assert
        assertEquals(expectedImagePath, actualImagePath);
    }

    @Test
    public void testGetCardImage5() {
        // Arrange
        Card card = new Card(12, 4);

        // Act
        String expectedImagePath = "Classic/s12.png";
        String actualImagePath = card.getCardImage();

        // Assert
        assertEquals(expectedImagePath, actualImagePath);
    }

    @Test
    public void testGetCardImage6() {
        // Arrange
        Card card = new Card(6, 2);

        // Act
        String expectedImagePath = "Classic/c06.png";
        String actualImagePath = card.getCardImage();

        // Assert
        assertEquals(expectedImagePath, actualImagePath);
    }

    @Test
    public void testGetCardImage7() {
        // Arrange
        Card card = new Card(4, 4);

        // Act
        String expectedImagePath = "Classic/s04.png";
        String actualImagePath = card.getCardImage();

        // Assert
        assertEquals(expectedImagePath, actualImagePath);
    }

    @Test
    public void testGetCardImage8() {
        // Arrange
        Card card = new Card(12, 2);

        // Act
        String expectedImagePath = "Classic/c12.png";
        String actualImagePath = card.getCardImage();

        // Assert
        assertEquals(expectedImagePath, actualImagePath);
    }

    @Test
    public void testGetCardImage9() {
        // Arrange
        Card card = new Card(10, 3);

        // Act
        String expectedImagePath = "Classic/h10.png";
        String actualImagePath = card.getCardImage();

        // Assert
        assertEquals(expectedImagePath, actualImagePath);
    }

    @Test
    public void testGetCardImage10() {
        // Arrange
        Card card = new Card(8, 1);

        // Act
        String expectedImagePath = "Classic/d08.png";
        String actualImagePath = card.getCardImage();

        // Assert
        assertEquals(expectedImagePath, actualImagePath);
    }
}