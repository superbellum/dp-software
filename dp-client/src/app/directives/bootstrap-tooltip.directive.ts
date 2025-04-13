import {AfterViewInit, Directive, effect, ElementRef, inject, input, OnInit, Renderer2} from '@angular/core';
import {TooltipPosition} from '../model/tooltip-position';

declare var bootstrap: any;

@Directive({
  selector: '[bootstrapTooltip]'
})
export class BootstrapTooltipDirective implements OnInit, AfterViewInit {
  bootstrapTooltip = input<string>("");
  bootstrapTooltipPosition = input<TooltipPosition>(TooltipPosition.TOP);

  private el = inject(ElementRef);
  private renderer = inject(Renderer2);

  // todo: fix
  constructor() {
    effect(() => {
      const newTooltip = this.bootstrapTooltip();
      console.log("b e", newTooltip);
      this.renderer.setAttribute(this.el.nativeElement, "data-bs-title", newTooltip);
    });
  }

  ngOnInit() {
    this.renderer.setAttribute(this.el.nativeElement, "data-bs-toggle", "tooltip");
    this.renderer.setAttribute(this.el.nativeElement, "data-bs-title", this.bootstrapTooltip());
    this.renderer.setAttribute(this.el.nativeElement, "data-bs-html", "true");

    switch (this.bootstrapTooltipPosition()) {
      case TooltipPosition.LEFT:
        this.renderer.setAttribute(this.el.nativeElement, "data-bs-placement", "left");
        break;
      case TooltipPosition.RIGHT:
        this.renderer.setAttribute(this.el.nativeElement, "data-bs-placement", "right");
        break;
      case TooltipPosition.BOTTOM:
        this.renderer.setAttribute(this.el.nativeElement, "data-bs-placement", "bottom");
        break;
      case TooltipPosition.TOP:
        this.renderer.setAttribute(this.el.nativeElement, "data-bs-placement", "top");
        break;
    }
  }

  ngAfterViewInit() {
    new bootstrap.Tooltip(this.el.nativeElement);
  }
}
