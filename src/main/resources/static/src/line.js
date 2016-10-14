export class Line {
  constructor(type, content, author) {
    this.type = type.trim();
    this.content = content.trim();
    this.author = author ? author.trim() : '';
  }
}
