const nodemailer = require('nodemailer');
require('dotenv').config();

class MailSender {
  constructor() {
    this._transporter = nodemailer.createTransport({
      host: 'smtp.gmail.com',
      port: 465,
      secure: true,
      auth: {
        user: process.env.MAIL_ADDRESS,
        pass: process.env.MAIL_PASSWORD,
      },
    });
  }

  sendEmail(targetEmail, content) {
    const message = {
      from: 'OPEN MUSIC API',
      to: targetEmail,
      subject: 'Export Songs',
      text: 'This is attachment for exporting song at playlist',
      attachments: [
        {
          filename: 'songs.json',
          content,
        },
      ],
    };
    console.log(message)
    return this._transporter.sendMail(message);
  }
}

module.exports = MailSender;
