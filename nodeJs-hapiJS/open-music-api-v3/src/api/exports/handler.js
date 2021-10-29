const errorHandler = require('../../utils/ErrorHandler');

class ExportsHandler {
  constructor(service, validator, playlistService) {
    this._service = service;
    this._playlistService = playlistService;
    this._validator = validator;

    this.postExportPlaylistHandler = this.postExportPlaylistHandler.bind(this);
  }

  async postExportPlaylistHandler(request, h) {
    try {
      this._validator.validateExportPlaylistsPayload(request.payload);
      const { playlistId } = request.params;
      const { id: userId } = request.auth.credentials;
      const { targetEmail } = request.payload;
      await this._playlistService.verifyPlaylistAccess(playlistId, userId);
      const message = {
        playlistId,
        targetEmail,
      };
      await this._service.sendMessage('export:playlists', JSON.stringify(message));
      const response = h.response({
        status: 'success',
        message: 'Permintaan Anda sedang kami proses',
      });
      response.code(201);
      return response;
    } catch (error) {
      return errorHandler(error, h);
    }
  }
}

module.exports = ExportsHandler;
