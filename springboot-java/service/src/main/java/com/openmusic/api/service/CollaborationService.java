
package com.openmusic.api.service;

import com.openmusic.api.entities.database.Collaborations;
import com.openmusic.api.models.response.PlaylistOwnerResponse;

import java.util.List;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: CollaborationService.java, v 0.1 2021‐10‐20 07.56 Ahmad Irfaan Hibatullah Exp $$
 */
public interface CollaborationService {
    Collaborations addCollaboration(String playlistId, String userId);
    Collaborations deleteCollaboration(String playlistId, String userId);
    Collaborations verifyCollaborator(String playlistId, String userId);
    Boolean verifyPlaylistAccess(String playlistId, String owner);
    List<PlaylistOwnerResponse> getAllPlaylist(String owner);
}