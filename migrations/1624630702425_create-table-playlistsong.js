exports.up = (pgm) => {
  pgm.createTable('playlistsongs', {
    id: {
      type: 'VARCHAR(50)',
      primaryKey: true,
    },
    playlist_id: {
      type: 'VARCHAR(50)',
      notNull: true,
    },
    song_id: {
      type: 'VARCHAR(50)',
      notNull: true,
    },
  });
  pgm.addConstraint('playlistsongs', 'fk_playlistsongs.songs_song_id', 'FOREIGN KEY(song_id) REFERENCES songs(id) ON DELETE CASCADE');
  pgm.addConstraint('playlistsongs', 'fk_playlistsongs.playlists_playlist_id', 'FOREIGN KEY(playlist_id) REFERENCES playlists(id) ON DELETE CASCADE');
};

exports.down = (pgm) => {
  // mengapus constraint terlebih dahulu ketika ingin drop table playlist
  pgm.dropConstraint('playlistsongs', 'fk_playlistsongs.songs_song_id');
  pgm.dropConstraint('playlistsongs', 'fk_playlistsongs.playlists_playlist_id');

  // mengapus constraint terlebih dahulu ketika ingin drop table playlist
  pgm.dropTable('playlistsongs');
};
