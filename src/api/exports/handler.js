const errorHandler = require('../../utils/ErrorHandler');

class ExportsHandler {
  constructor(service, validator) {
    this._service = service;
    this._validator = validator;

    this.postExportPlaylistHandler = this.postExportPlaylistHandler.bind(this);
  }

  async postExportPlaylistHandler(request, h) {
    try {
      const { id: userId } = request.auth.credentials;
      const { playlistId } = request.params;
      const { targetEmail } = request.payload.targetEmail;

      this._validator.validateExportPlaylistsPayload(request.payload);
      await this._service.verifyPlaylistAccess(playlistId, userId);

      const message = {
        userId,
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
