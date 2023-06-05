package it.uniroma3.diadia.ambienti; // nord sud est ovest

public enum Direzione {

	nord(0) {
		@Override
		public Direzione opposta() {
			return Direzione.sud;
		}
	},
	sud(360) {
		@Override
		public Direzione opposta() {
			return Direzione.nord;
		}
	},
	est(90) {
		@Override
		public Direzione opposta() {
			return Direzione.ovest;
		}
	},
	ovest(270) {
		@Override
		public Direzione opposta() {
			return Direzione.est;
		}
	};

	private final int gradi;

	private Direzione(int gradi) {
		this.gradi = gradi;
	}

	public int getGradi() {
		return this.gradi;
	}

	public static Direzione opposta(Direzione direzione) {
		switch (direzione) {
		case nord:
			return Direzione.sud;
		case sud:
			return Direzione.nord;
		case est:
			return Direzione.ovest;
		case ovest:
			return Direzione.est;
		default:
			return null;
		}
	}

	public abstract Direzione opposta();
}