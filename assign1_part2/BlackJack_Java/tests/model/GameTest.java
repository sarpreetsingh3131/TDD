package model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class GameTest {

	Game sut;
	Player mockPlayer = mock(Player.class);
	Dealer mockDealer = mock(Dealer.class);
	Game spy;

	@Before
	public void setUp() throws Exception {
		sut = new Game();
		sut = sut.makeNewGame(mockPlayer, mockDealer);

		spy = spy(sut);
		when(spy.getDealer()).thenReturn(mockDealer);
	}

	@Test
	public void shouldCreateNewGameObject() {
		assertNotNull(sut);
	}

	@Test
	public void shouldReturnDealer() {
		assertNotNull(sut.getDealer());
	}

	@Test
	public void shouldReturnPlayer() {
		assertNotNull(sut.getPlayer());
	}

	@Test
	public void shouldReturnFalseBecauseGameIsOver() {
		when(mockDealer.isGameOver()).thenReturn(false);

		assertFalse(sut.isGameOver());
		verify(mockDealer, times(1)).isGameOver();
	}

	@Test
	public void shouldReturnTrueBecauseDealerIsWinner() {
		when(mockDealer.isDealerWinner(mockPlayer)).thenReturn(true);

		assertTrue(sut.isDealerWinner());
		verify(mockDealer, times(1)).isDealerWinner(mockPlayer);
	}

	@Test
	public void shouldReturnTrueBecauseNewGameHasStarted() {
		when(mockDealer.newGame(mockPlayer)).thenReturn(true);

		assertTrue(sut.newGame());
		verify(mockDealer, times(1)).newGame(mockPlayer);
	}

	@Test
	public void shouldReturnTrueBecauseDealerCanHit() {
		when(mockDealer.hit(mockPlayer, true)).thenReturn(true);

		assertTrue(sut.hit());
		verify(mockDealer, times(1)).hit(mockPlayer, true);
	}

	@Test
	public void shouldReturnTrueBecauseDealerCanStand() {
		when(mockDealer.stand(mockPlayer)).thenReturn(true);

		assertTrue(sut.stand());
		verify(mockDealer, times(1)).stand(mockPlayer);
	}

	@Test
	public void shouldReturn17AsDealerScore() {
		when(mockDealer.calcScore()).thenReturn(17);

		assertEquals(17, sut.getDealerScore());
		verify(mockDealer, times(1)).calcScore();
	}

	@Test
	public void shouldReturn15AsPlayerScore() {
		when(mockPlayer.calcScore()).thenReturn(15);

		assertEquals(15, sut.getPlayerScore());
		verify(mockPlayer, times(1)).calcScore();
	}

	@Test
	public void shouldReturnAListOfPlayerCards() {
		setSpyListWithSize(mockPlayer, 3);

		assertEquals(3, sut.getPlayerHand().size());
		verify(mockPlayer, times(1)).getHand();
	}

	@Test
	public void shouldReturnAListOfDealerCards() {
		setSpyListWithSize(mockDealer, 3);
		
		assertEquals(3, sut.getDealerHand().size());
		verify(mockDealer, times(1)).getHand();
	}

	private void setSpyListWithSize(Player player, int size) {
		ArrayList<Card> spy = spy(new ArrayList<Card>());
		when(spy.size()).thenReturn(size);
		when(player.getHand()).thenReturn(spy);
	}
}