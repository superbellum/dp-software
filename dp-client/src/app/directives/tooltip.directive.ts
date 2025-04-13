import {Directive, effect, ElementRef, HostListener, inject, input, OnDestroy, Renderer2} from '@angular/core';
import {TooltipPosition} from '../model/tooltip-position';

@Directive({
  selector: '[tooltip]'
})
export class TooltipDirective implements OnDestroy {
  tooltip = input<string>("");
  tooltipPosition = input<TooltipPosition>(TooltipPosition.TOP);
  tooltipFontSize = input<number>(15);
  toolTipOffset = input<number>(5);
  private tooltipElement!: HTMLElement;

  private el = inject(ElementRef);
  private renderer = inject(Renderer2);

  constructor() {
    effect(() => {
      const newTooltip = this.tooltip();
      if (this.tooltipElement) {
        this.tooltipElement.innerHTML = newTooltip;
      }
    });
  }

  ngOnDestroy() {
    this.removeTooltip();
  }

  @HostListener('mouseenter') onMouseEnter() {
    this.tooltipElement = this.renderer.createElement('div');
    this.renderer.addClass(this.tooltipElement, 'tooltip-style');
    this.renderer.addClass(this.tooltipElement, 'tooltip-fade-in');
    this.renderer.setStyle(this.tooltipElement, 'fontSize', `${this.tooltipFontSize()}px`);
    this.tooltipElement.innerHTML = this.tooltip();
    this.renderer.appendChild(document.body, this.tooltipElement);
    const hostPos = this.el.nativeElement.getBoundingClientRect();
    const tooltipPos = this.tooltipElement.getBoundingClientRect();

    switch (this.tooltipPosition()) {
      case TooltipPosition.LEFT:
        this.renderer.setStyle(this.tooltipElement, 'top', `${hostPos.top + hostPos.height / 2}px`);
        this.renderer.setStyle(this.tooltipElement, 'left', `${hostPos.left - tooltipPos.width - this.toolTipOffset()}px`);
        this.renderer.setStyle(this.tooltipElement, 'transform', 'translateY(-50%)');
        break;
      case TooltipPosition.RIGHT:
        this.renderer.setStyle(this.tooltipElement, 'top', `${hostPos.top + hostPos.height / 2}px`);
        this.renderer.setStyle(this.tooltipElement, 'left', `${hostPos.right + this.toolTipOffset()}px`);
        this.renderer.setStyle(this.tooltipElement, 'transform', 'translateY(-50%)');
        break;
      case TooltipPosition.TOP:
        this.renderer.setStyle(this.tooltipElement, 'top', `${hostPos.y - tooltipPos.height - this.toolTipOffset()}px`);
        this.renderer.setStyle(this.tooltipElement, 'left', `${hostPos.left + hostPos.width / 2}px`);
        this.renderer.setStyle(this.tooltipElement, 'transform', 'translateX(-50%)');
        break;
      case TooltipPosition.BOTTOM:
        this.renderer.setStyle(this.tooltipElement, 'top', `${hostPos.y + hostPos.height + this.toolTipOffset()}px`);
        this.renderer.setStyle(this.tooltipElement, 'left', `${hostPos.left + hostPos.width / 2}px`);
        this.renderer.setStyle(this.tooltipElement, 'transform', 'translateX(-50%)');
        break;
    }
  }

  @HostListener('mouseleave') onMouseLeave() {
    this.removeTooltip();
  }

  private removeTooltip() {
    if (this.tooltipElement) {
      this.renderer.removeClass(this.tooltipElement, 'tooltip-fade-in');
      this.renderer.addClass(this.tooltipElement, 'tooltip-fade-out');
      setTimeout(() => this.renderer.removeChild(document.body, this.tooltipElement), 100); // match fadeOut duration
    }
  }
}
