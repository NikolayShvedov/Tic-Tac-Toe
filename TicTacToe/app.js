let game = document.querySelector('.game'),
	res = document.querySelector('.res'),
	btnGame = document.querySelector('.new-game'),
	fields = document.querySelectorAll('.field'),
	step = false,
	count = 0,
	circle = `<svg class="circle">
				<circle r="45" cx="58" cy="58" stroke="blue" stroke-width="10" fill="none" stroke-linecap="round" />
			</svg>`,
	cross = `<svg class="cross">
				<line class="first" x1="15" y1="15" x2="100" y2="100" stroke="red" stroke-width="10" stroke-linecap="round" />
				<line class="second" x1="100" y1="15" x2="15" y2="100" stroke="red" stroke-width="10" stroke-linecap="round" />
			</svg>`;

function stepCross(target) {
	target.innerHTML = cross;
	target.classList.add('x');
	let crossAudio = new Audio('audio/cross.mp3');
	crossAudio.play();
	count++;
}
function stepZero(target) {
	target.innerHTML = circle;
	target.classList.add('o');
	let circleAudio = new Audio('audio/zero.mp3');
	circleAudio.play();
	count++;
}

function init(e) {
	if (!step) stepCross(e.target);
	else stepZero(e.target);
	step = !step;
	win();
}

function newGame() {
	step = false;
	count = 0;
	res.innerText = '';
	fields.forEach(item => {
		item.innerHTML = '';
		item.classList.remove('x', 'o', 'active');
	});
	game.addEventListener('click', init);
}

function win() {
	let comb = [
		[0, 1, 2],
		[3, 4, 5],
		[6, 7, 8],
		[0, 3, 6],
		[1, 4, 7],
		[2, 5, 8],
		[0, 4, 8],
		[2, 4, 6]
	];

	for (let i = 0; i < comb.length; i++) {

		if (fields[comb[i][0]].classList.contains('x') &&
			fields[comb[i][1]].classList.contains('x') &&
			fields[comb[i][2]].classList.contains('x')) {
			setTimeout(() => {
				fields[comb[i][0]].classList.add('active');
				fields[comb[i][1]].classList.add('active');
				fields[comb[i][2]].classList.add('active');
				res.innerText = 'Выиграли X';
			}, 1500);
			game.removeEventListener('click', init);
		}

		else if (fields[comb[i][0]].classList.contains('o') &&
			fields[comb[i][1]].classList.contains('o') &&
			fields[comb[i][2]].classList.contains('o')) {
			setTimeout(() => {
				fields[comb[i][0]].classList.add('active');
				fields[comb[i][1]].classList.add('active');
				fields[comb[i][2]].classList.add('active');
				res.innerText = 'Выиграли O';
			}, 1500);
			game.removeEventListener('click', init);
		}

		else if (count == 9) {
			res.innerText = 'Ничья';
			game.removeEventListener('click', init);
		}

	}

}

btnGame.addEventListener('click', newGame);
game.addEventListener('click', init);