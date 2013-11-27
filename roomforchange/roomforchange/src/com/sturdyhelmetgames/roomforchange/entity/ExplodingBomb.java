package com.sturdyhelmetgames.roomforchange.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.sturdyhelmetgames.roomforchange.assets.Assets;
import com.sturdyhelmetgames.roomforchange.level.Level;

public class ExplodingBomb extends Bomb {

	private final Rectangle explosionRadius = new Rectangle(0f, 0f, 3f, 3f);

	public ExplodingBomb(float x, float y, Level level) {
		super(x, y, level);
	}

	@Override
	public void render(float delta, SpriteBatch batch) {
		// super.render(delta, batch);

		// calculate scale
		final float scale = getScale();
		batch.draw(Assets.bomb.getKeyFrame(stateTime), bounds.x, bounds.y
				- 0.5f + zz, 0f, 0f, 1f, 1f, scale, scale, 0f);
	}

	@Override
	public void update(float fixedStep) {
		// super.update(fixedStep);
		stateTime += fixedStep;
		if (stateTime > 3f && !isDead() && !isDying()) {
			aliveTick = ALIVE_TIME_MAX;
			explosionRadius.setPosition(bounds.x - 1.5f, bounds.y - 1.5f);
			level.addParticleEffect(Assets.PARTICLE_EXPLOSION, bounds.x + 0.5f,
					bounds.y + 0.5f);
			Assets.getGameSound(Assets.SOUND_EXPLOSION).play(0.7f);
			for (int i = 0; i < level.entities.size; i++) {
				final Entity entity = level.entities.get(i);
				entity.hit(explosionRadius);
			}
		}
	}

}
