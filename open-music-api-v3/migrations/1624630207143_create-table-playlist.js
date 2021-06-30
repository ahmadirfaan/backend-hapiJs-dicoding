exports.up = (pgm) => {
  pgm.createTable('playlists', {
    id: {
      type: 'VARCHAR(50)',
      primaryKey: true,
    },
    name: {
      type: 'VARCHAR(50)',
      notNull: true,
    },
    owner: {
      type: 'VARCHAR(50)',
      notNull: true,
    },
  });
  pgm.addConstraint('playlists', 'fk_playlists.owner_user_id', 'FOREIGN KEY(owner) REFERENCES users(id) ON DELETE CASCADE');
};

exports.down = (pgm) => {
  // mengapus constraint terlebih dahulu ketika ingin drop table playlist
  pgm.dropConstraint('playlists', 'fk_playlists.owner_user_id');

  // mengapus constraint terlebih dahulu ketika ingin drop table playlist
  pgm.dropTable('playlists');
};
